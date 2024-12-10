import { IconPencil, IconTrash } from '@tabler/icons-react';
import { ActionIcon, Group, Table, Text } from '@mantine/core';

const data = [
    {
        name: 'Robert Wolfkisser',
    },
    {
        name: 'Jill Jailbreaker',
    },
    {
        name: 'Henry Silkeater',
    },
    {
        name: 'Bill Horsefighter',
    },
    {
        name: 'Jeremy Footviewer',
    },
];

export default function ListTeacherComponent() {
    const rows = data.map((item) => (
        <Table.Tr key={item.name}>
            <Table.Td>
                <Group gap="sm">
                    <Text fz="sm" fw={500}>
                        {item.name}
                    </Text>
                </Group>
            </Table.Td>
            <Table.Td>
                <Group gap={0} justify="flex-end">
                    <ActionIcon variant="subtle" color="gray">
                        <IconPencil size={16} stroke={1.5} />
                    </ActionIcon>
                    <ActionIcon variant="subtle" color="red">
                        <IconTrash size={16} stroke={1.5} />
                    </ActionIcon>
                </Group>
            </Table.Td>
        </Table.Tr>
    ));

    return (
        <Table.ScrollContainer minWidth={800}>
            <Table verticalSpacing="sm">
                <Table.Tbody>{rows}</Table.Tbody>
            </Table>
        </Table.ScrollContainer>
    );
}