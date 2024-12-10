import {
    ActionIcon, Box,
    Button,
    Divider,
    Group, NativeSelect,
    Paper,
    PaperProps,
    Stack,
    Text,
    TextInput,
} from '@mantine/core';
import { useForm } from '@mantine/form';
import {Link} from "react-router-dom";
import { IoCloseCircleOutline } from "react-icons/io5";

export function CreateGroupComponent(props: PaperProps) {
    const disciplines = ["Math", "Physics", "Programming"]
    const form = useForm({
        initialValues: {
            name: '',
            discipline: '',
        },
    });
    return (
        <Paper radius="md" p="xl" pt={"5"} withBorder {...props}>
            <Box display={"flex"} ml={"100%"}>
                <Link to={"/groups-students"}>
                    <ActionIcon  radius={100} variant="subtle" color="red">
                        <IoCloseCircleOutline size={32}/>
                    </ActionIcon>
                </Link>
            </Box>
            <Text size="lg" ta={"center"} fw={500}>
                Группа
            </Text>

            <Divider label={'Создание'} labelPosition="center" my="lg" />

            <form onSubmit={form.onSubmit(() => {})}>
                <Stack>
                    <TextInput
                        label="Название"
                        placeholder="Название Группы"
                        value={form.values.name}
                        onChange={(event) => form.setFieldValue('name', event.currentTarget.value)}
                        radius="md"
                    />
                    <NativeSelect
                        radius="md"
                        data={disciplines}
                        label="Группа"
                        value={form.values.discipline}
                        onChange={(event) => form.setFieldValue('discipline', event.currentTarget.value)}
                    />
                </Stack>

                <Group justify="end" mt="xl">
                    <Button type="submit" radius="xl" >
                        Добавить Группу
                    </Button>
                </Group>
            </form>
        </Paper>
    );
}